package com.bicycle.racing.service;

public class PresenterService {

//    private final TrackService trackService;
//    private final EventServiceImpl eventService;
//    private final AnalysisOfTrack analysisOfTrack;
//    private final EventResultService eventResultService;
//
//    @Autowired
//    public PresenterService(TrackService trackService,
//                            EventServiceImpl eventService,
//                            AnalysisOfTrack analysisOfTrack,
//                            EventResultService eventResultService) {
//
//        this.trackService = trackService;
//        this.eventService = eventService;
//        this.analysisOfTrack = analysisOfTrack;
//        this.eventResultService = eventResultService;
//    }
//
//    public boolean saveUserTrackByEventId(UserFileForm userFileForm) {
//        GPX gpx = getGpx(userFileForm.getTrackFile());
//
//        if (gpx != null) {
//            List<Waypoint> trackWaypoints = analysisOfTrack.getTrackWaypoints(gpx);
//            trackService.saveTrackAndWaypoints(userFileForm, trackWaypoints);
//
//            List<Waypoint> simpleWaypoints = trackService.getSimpleWaypointsByTrackId(userFileForm.getEventId());
//            Event event = eventService.getEventById(userFileForm.getEventId());
//
//            EventResult eventResult = analysisOfTrack.analysisTrack(event, gpx, simpleWaypoints);
//            eventResult.setEventId(userFileForm.getEventId());
//            eventResult.setUsername(userFileForm.getUsername());
//            eventResultService.saveUserResult(eventResult);
//
//            return true;
//        }
//        return false;
//    }
//
//    public boolean saveEventAndSampleTrack(Event event, MultipartFile trackFile) {
//        GPX gpx = getGpx(trackFile);
//
//        if (gpx != null) {
//            int trackId = trackService.saveSampleTrackAndWaypoint(trackFile.getOriginalFilename(), gpx);
//            event.setSampleTrackId(trackId);
//            eventService.saveEvent(event);
//
//            return true;
//        }
//        return false;
//    }
//
//    private GPX getGpx(MultipartFile trackFile) {
//        try (InputStream inputStream = trackFile.getInputStream()) {
//
//            return analysisOfTrack.parseGpxFile(inputStream);
//        } catch (IOException e) {
//            System.err.println("Failed get stream");
//
//            return null;
//        }
//    }
}
